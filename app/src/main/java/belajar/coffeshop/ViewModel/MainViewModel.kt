package belajar.coffeshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import belajar.coffeshop.Model.CategoryModel
import belajar.coffeshop.Model.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MainViewModel:ViewModel() {
//    utk mendapatkan instance dari Firebase Realtime Database
    private val firebaseDatabase = FirebaseDatabase.getInstance()

//    MutableLiveData adalah komponen dari Android Jetpack yang memungkinkan data untuk dipantau oleh UI, dan secara otomatis memperbarui UI ketika data berubah. MutableLiveData juga mendukung modifikasi data.
//    Dalam hal ini, _category menyimpan data berupa daftar (list) kategori (CategoryModel) yang dapat dimodifikasi.
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _popular = MutableLiveData<MutableList<ItemsModel>>()
    private val _offer = MutableLiveData<MutableList<ItemsModel>>()


//    LiveData yang memungkinkan UI mengamati perubahan data kategori, tetapi tidak bisa mengubahnya langsung.
//    Ini digunakan untuk memisahkan data yang bisa dimodifikasi secara internal dengan data yang hanya dapat diamati oleh UI.
    val category:LiveData<MutableList<CategoryModel>> = _category
    var popular: LiveData<MutableList<ItemsModel>> = _popular
    var offer: LiveData<MutableList<ItemsModel>> = _offer

//    mengambil data kategori dari Firebase Realtime Database.
    fun loadCategory(){
        val Ref = firebaseDatabase.getReference("Category")

//    Menambahkan ValueEventListener ke Ref. Listener ini akan memantau perubahan data di node "Category".
        Ref.addValueEventListener(object : ValueEventListener{

//            onDataChange() dipanggil setiap kali ada perubahan pada data di lokasi yang dipantau. Parameter snapshot adalah snapshot dari data yang diambil dari lokasi database.
            override fun onDataChange(snapshot: DataSnapshot) {

//                lists adalah daftar kosong yang akan digunakan untuk menyimpan data kategori yang didapat dari snapshot.
                val lists = mutableListOf<CategoryModel>()

                for (childSnaphot in snapshot.children){

//                    yang mengonversi data snapshot menjadi objek CategoryModel.
                    val list = childSnaphot.getValue(CategoryModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }

//    Setelah semua data kategori berhasil diambil dan dimasukkan ke dalam lists, data tersebut dimasukkan ke dalam _category.
                _category.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadPopular(){
        var Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()

                for(childSnapshot in snapshot.children){
                    var list = childSnapshot.getValue(ItemsModel::class.java)
                    if(list != null){
                        lists.add(list)
                    }
                }

                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadOffer(){
        var Ref = firebaseDatabase.getReference("Offers")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()

                for(childSnapshot in snapshot.children){
                    var list = childSnapshot.getValue(ItemsModel::class.java)
                    if(list != null){
                        lists.add(list)
                    }
                }

                _offer.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}