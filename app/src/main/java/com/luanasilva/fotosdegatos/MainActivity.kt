package com.luanasilva.fotosdegatos

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luanasilva.fotosdegatos.adapter.GatoAdapter
import com.luanasilva.fotosdegatos.api.RetrofitHelper
import com.luanasilva.fotosdegatos.databinding.ActivityMainBinding
import com.luanasilva.fotosdegatos.model.GatoResposta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val gatoAPI by lazy {
        RetrofitHelper.gatoAPI
    }

    private lateinit var gatoAdapter: GatoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recuperarImagensDeGatos()


        binding.rvGatos.layoutManager = GridLayoutManager(
            this,
            3,
            RecyclerView.VERTICAL,
            false
        )

    }





    private fun recuperarImagensDeGatos() {
        var listaURLGatos = mutableListOf<String>()
        CoroutineScope(Dispatchers.IO).launch {

            var resposta: Response<GatoResposta>? = null

            try {
                resposta = gatoAPI.recuperarImagensGatos()

                if (resposta != null && resposta.isSuccessful) {
                    val gatoResposta = resposta.body()
                    gatoResposta?.forEach { gato ->
                        val gatoURL = gato.url
                        listaURLGatos.add(gatoURL)

                    }
                }


            } catch (erro: Exception) {
                erro.printStackTrace()
                Log.i("TAG_GATOS", "recuperarImagensDeGatos: ${erro.message}")
            }



            withContext(Dispatchers.Main) {
                gatoAdapter = GatoAdapter()
                binding.rvGatos.adapter = gatoAdapter
                gatoAdapter.adicionarLista(listaURLGatos)

            }
        }
    }





}