package com.example.introductiontoroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.introductiontoroom.databinding.FragmentAddEditPersonBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddEditPersonFragment(private val listener: AddEditPersonListener) : BottomSheetDialogFragment() {

    private lateinit var binding :FragmentAddEditPersonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddEditPersonBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachUiListener()
    }

    private fun attachUiListener(){

        binding.saveBtn.setOnClickListener {
            val name = binding.personNameEt.text.toString()
            val age = binding.personAgeEt.text.toString()
            val city = binding.personCityEt.text.toString()
            if (name.isNotEmpty() && age.isNotEmpty() && city.isNotEmpty()){

                val person =PersonModel(0,name,age.toInt(),city)
                listener.onSaveBtnClicked(person)
            }
            dismiss()

        }
    }

    companion object {

        const val TAG = "AddEditPersonFragment"
    }
    interface AddEditPersonListener {
        fun onSaveBtnClicked(personModel: PersonModel)
    }
}