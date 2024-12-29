package com.example.introductiontoroom

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.introductiontoroom.databinding.SingleItemBinding

class PersonDetailsAdapter :
    ListAdapter<PersonModel, PersonDetailsAdapter.PersonDetailsViewHolder>(DiffUtilCallBack()) {

    inner class PersonDetailsViewHolder(private val binding: SingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            @SuppressLint("SetTextI18n")
            fun bind(personModel: PersonModel) {
                binding.personNameTv.text = personModel.name
                binding.personAgeTv.text = personModel.age.toString()
                binding.personCityTv.text = personModel.city
            }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<PersonModel>() {

        override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel) =
            oldItem.pId == newItem.pId

        override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDetailsViewHolder {
        return PersonDetailsViewHolder(
            SingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PersonDetailsViewHolder, position: Int) {
       holder.bind(getItem(position))
    }
}