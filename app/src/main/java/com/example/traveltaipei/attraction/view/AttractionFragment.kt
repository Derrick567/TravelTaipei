package com.example.traveltaipei.attraction.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.traveltaipei.R
import com.example.traveltaipei.home.model.Attraction
import com.example.traveltaipei.attraction.viewmodel.AttractionViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AttractionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AttractionFragment : Fragment() {
    //private var id: Int? = null
    private val viewModel: AttractionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //id = it.getInt(ATTRACTION_ID)
            it.getParcelable<Attraction>("attraction")?.let { attraction ->
                viewModel.attraction =  attraction
            }
            Log.d("AttractionFragment", "id = $id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {

            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MaterialTheme {
                    AttractionScreen(onBack = {activity?.supportFragmentManager?.popBackStack()}
                    , onUrlClick = { url ->
                        val navDirect = AttractionFragmentDirections.attractionFragmentToWebviewFragment(url)
                        findNavController().navigate(navDirect)})
                }
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}