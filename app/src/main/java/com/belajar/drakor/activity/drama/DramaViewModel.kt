package com.belajar.drakor.activity.drama

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DramaViewModel : ViewModel() {
    private val _dramaList = MutableLiveData<List<Drama>>()
    val dramaList: LiveData<List<Drama>> = _dramaList

    private val _favoriteList = MutableLiveData<List<Drama>>()
    val favoriteList: LiveData<List<Drama>> = _favoriteList

    private val favoriteDramas = mutableListOf<Drama>()

    init {
        // Initialize with sample data
        _dramaList.value = getDramaList()
        _favoriteList.value = emptyList()
    }

//    fun addOrRemoveFavorite(drama: Drama){
//        drama.isFavorite = !drama.isFavorite
//        if(drama.isFavorite){
//            _favoriteList.value = _favoriteList.value?.plus(drama)
//        } else {
//            _favoriteList.value = _favoriteList.value?.minus(drama)
//        }
//        //Update the drama list
//        _dramaList.value = _dramaList.value?.map {
//            if (it.title == drama.title) drama else it
//        }
//
//        if (favoriteDramas.contains(drama)) {
//            favoriteDramas.remove(drama)
//        } else {
//            favoriteDramas.add(drama)
//        }
//        _favoriteList.value = favoriteDramas.toList()
//    }

    private fun getDramaList(): List<Drama> {
        return listOf(
            Drama("Crash Landing on You", "https://i.pinimg.com/236x/fa/cf/fd/facffd892be28a9ce217b16e6107952e.jpg"),
            Drama("Descendants of the Sun", "https://i.pinimg.com/236x/65/5a/c4/655ac4615531b39f16f8a3aa5056aef5.jpg"),
            Drama("Goblin", "https://i.pinimg.com/236x/9a/1d/f1/9a1df1002d29d5bd1dddf5678498161c.jpg"),
            Drama("Itaewon Class", "https://i.pinimg.com/236x/60/50/55/605055d4d2831f23fe0b6cb3282859bf.jpg"),
            Drama("Reply 1988", "https://i.pinimg.com/236x/bf/01/d4/bf01d49fdbb244a2c422910b3a2dd4bc.jpg"),
            Drama("Lovely Runner", "https://i.pinimg.com/474x/03/d6/f5/03d6f5731b89df0eefd9908c9bb714e7.jpg"),
            Drama("Queen of Tears", "https://i.pinimg.com/474x/1a/e2/12/1ae2124230d388de36b625bdf80e427e.jpg"),
            Drama("A Shop For Killers", "https://i.pinimg.com/474x/15/18/73/151873efb0aaa2547a8f5ba8f8b2964a.jpg"),
            Drama("Extraordinary You", "https://i.pinimg.com/736x/4b/4a/3a/4b4a3aab3d560140167cd35891fd8c93.jpg")
        )
    }
}