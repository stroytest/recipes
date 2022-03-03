package com.abstudio.mytodo.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.abstudio.recipes.data.entities.Category

class CategoriesDiffUtil(private val oldList: List<Category>,
                         private val newList: List<Category>
                   ): DiffUtil.Callback()
{
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].photoPath == newList[newItemPosition].photoPath
                && oldList[oldItemPosition].oldId == newList[newItemPosition].oldId
    }
}