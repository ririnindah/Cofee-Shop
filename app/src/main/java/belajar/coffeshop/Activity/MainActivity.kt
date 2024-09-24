package belajar.coffeshop.Activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import belajar.coffeshop.Adapter.CategoryAdapter
import belajar.coffeshop.Adapter.OfferAdapter
import belajar.coffeshop.Adapter.PopularAdapter
import belajar.coffeshop.R
import belajar.coffeshop.ViewModel.MainViewModel
import belajar.coffeshop.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    lateinit var binding : ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Menginisialisasi binding menggunakan layout inflater. Ini menghubungkan layout XML (activity_main.xml) dengan binding yang memungkinkan kita mengakses view di dalam layout secara langsung melalui binding.
        binding = ActivityMainBinding.inflate(layoutInflater)

//        Mengatur root view dari binding sebagai konten untuk ditampilkan di Activity.
        setContentView(binding.root)

        initCategory()
        initPopular()
        initOffers()
    }

    private fun initOffers() {
        binding.progressBarOffer.visibility = View.VISIBLE
        viewModel.offer.observe(this, Observer {
            binding.recyclerViewOffer.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewOffer.adapter = OfferAdapter(it)
            binding.progressBarOffer.visibility = View.GONE
        })

        viewModel.loadOffer()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility=View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.recyclerViewPopular.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })

        viewModel.loadPopular()
    }

    private fun initCategory() {
//        menampilkan progressbarCategory
        binding.progressBarCategory.visibility = View.VISIBLE
//        Mengamati LiveData kategori dari viewModel. Saat data diubah, Observer akan dieksekusi dan memperbarui UI.
        viewModel.category.observe(this, Observer {

//            mengatur layout categorinya, akan ditampilkan secara horizontal
            binding.recyclerViewCategory.layoutManager =
                LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }
}