package daboya.task.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import daboya.task.R
import daboya.task.adapter.ImageAdapter
import daboya.task.databinding.ActivityMainBinding
import daboya.task.extras.isConnectedToInternet
import daboya.task.viewmodel.MyViewModel

class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()

        if (isConnectedToInternet(this)){
            myViewModel.getImages(this,imageAdapter,binding.imagesRcv).observe(this, Observer { list ->
                imageAdapter.submitList(list)
                imageAdapter.notifyDataSetChanged()
            })
        }else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
        }


    }

    private fun init() {
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding.imagesRcv.layoutManager = LinearLayoutManager(this)
        imageAdapter = ImageAdapter(this)
        binding.imagesRcv.adapter = imageAdapter
    }
}