package io.github.iampjeetsingh.assignment.viewmodels

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import io.github.iampjeetsingh.assignment.models.User
import io.github.iampjeetsingh.assignment.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository, private val context: Context
) : ViewModel() {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("DATA", MODE_PRIVATE)
    private val showBannerObj = MutableLiveData<Boolean>()
    private val DATABASE_POPULATED = "DatabasePopulated"
    private val BANNER_CLOSED = "BannerClosed";

    val showBanner: LiveData<Boolean>
    get() = showBannerObj

    init {
        if(!sharedPreferences.getBoolean(DATABASE_POPULATED, false)) {
            insertUsers()
        }
        showBannerObj.postValue(sharedPreferences.getBoolean(BANNER_CLOSED, false))
    }

    fun closeBanner(){
        val editor = sharedPreferences.edit();
        editor.putBoolean(BANNER_CLOSED, true).apply();
        showBannerObj.postValue(true)
    }

    fun getUsers(): LiveData<List<User>> {
        return userRepository.getUsers()
    }

    private fun insertUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUsers(loadUserFromAssets()!!.asList())
            val editor = sharedPreferences.edit();
            editor.putBoolean(DATABASE_POPULATED, true).apply();
        }
    }

    private fun loadUserFromAssets(): Array<User>? {
        val inputStream = context.assets.open("users.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<User>::class.java)
    }

}