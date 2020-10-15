package com.example.mvvm_jokesapi


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_jokesapi.database.DatabaseJokes
import com.example.mvvm_jokesapi.databinding.FragmentFirstBinding
import com.example.mvvm_jokesapi.recycler_adapters.JokeClick
import com.example.mvvm_jokesapi.recycler_adapters.JokesAdapter
import com.example.mvvm_jokesapi.viewmodel.JokeListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var viewModelAdapter: JokesAdapter?= null

    private val viewModel : JokeListViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentFirstBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewmodel = viewModel
        viewModelAdapter = JokesAdapter(JokeClick {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })
        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = viewModelAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserverToGetData ()
    }

    fun setUpObserverToGetData(){
        viewModel.jokeListResults.observe(viewLifecycleOwner,
            Observer<List<DatabaseJokes>> {joke->
                joke.apply {
                    viewModelAdapter?.results = joke
                    //Timber.i(joke.get(2).id)
                }
            })
    }

}