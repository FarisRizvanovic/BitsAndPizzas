package com.faris.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.faris.bitsandpizzas.databinding.FragmentOrderBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment, commented because binding is used
//        val view = inflater.inflate(R.layout.fragment_order, container, false)

        //adding viewBinding
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root

//        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

//        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        binding.fab.setOnClickListener {
//            val pizzaGroup = view.findViewById<RadioGroup>(R.id.pizza_group)
            val pizzaType = binding.pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1) {
                val noPizzaSelectedText = "You need to chose a pizza type!"
                Toast.makeText(activity, noPizzaSelectedText, Toast.LENGTH_SHORT).show()
            } else {
                var order = (when (pizzaType) {
                    R.id.radio_diavolo -> "Diavolo pizza"
                    else -> "Fungi pizza"
                })

//                val parmesan = view.findViewById<Chip>(R.id.parmesan)
                order += if (binding.parmesan.isChecked) ", extra parmesan" else ""
//                val chiliOil = view.findViewById<Chip>(R.id.chili_oil)
                order += if (binding.chiliOil.isChecked) ", extra chili oil" else ""
                order += "."
                Snackbar.make(binding.fab, order, Snackbar.LENGTH_LONG).show()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}