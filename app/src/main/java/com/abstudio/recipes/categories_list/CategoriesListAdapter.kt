package com.abstudio.recipes.categories_list

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abstudio.mytodo.fragments.list.adapter.CategoriesDiffUtil
import com.abstudio.recipes.R
import com.abstudio.recipes.data.entities.Category

class CategoriesListAdapter : RecyclerView.Adapter<CategoriesListAdapter.MyViewHolder>() {

    var dataList = emptyList<Category>()
    lateinit var longClickListener: OnCategoryItemLongClick

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesListAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CategoriesListAdapter.MyViewHolder, position: Int) {

        holder.category_name_txt.text = dataList[position].name
        holder.recipes_amount_txt.text = getRecipesCount()
        //category_photo_img


        holder.row_itemview.setOnClickListener(View.OnClickListener {
            /*val action =
                ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)*/
        })

        holder.row_itemview.setOnLongClickListener(View.OnLongClickListener {
            val popup = PopupMenu(holder.itemView.context, holder.itemView)
            popup.inflate(R.menu.item_popup_menu)

            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
                when (item!!.itemId) {
                    R.id.menu_edit -> {
                        val category = dataList.get(position)
                        longClickListener.editCategory(category)
                        Toast.makeText(holder.itemView.context, category.name, Toast.LENGTH_SHORT).show()
                    }
                    R.id.menu_delete -> {
                        val category = dataList.get(position)
                        longClickListener.deleteCategory(category)
                        Toast.makeText(holder.itemView.context, category.name, Toast.LENGTH_SHORT).show()
                    }
                }
                true
            })

            popup.show()
            return@OnLongClickListener false
        })
    }

    private fun getRecipesCount(): String {
        return "Всего рецептов: "
    }

    fun setData(categoriesList: List<Category>) {
        val toDoDiffUtil = CategoriesDiffUtil(dataList, categoriesList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = categoriesList
        toDoDiffResult.dispatchUpdatesTo(this)
    }

    fun setOnClickListener(listener: OnCategoryItemLongClick){
        longClickListener = listener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category_photo_img = itemView.findViewById<ImageView>(R.id.category_photo_img)
        val category_name_txt = itemView.findViewById<TextView>(R.id.category_name_txt)
        val recipes_amount_txt = itemView.findViewById<TextView>(R.id.recipes_amount_txt)
        val row_itemview = itemView.findViewById<ConstraintLayout>(R.id.row_itemview)
    }
}