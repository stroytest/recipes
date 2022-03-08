package com.abstudio.recipes.categories_list

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abstudio.recipes.R
import com.abstudio.recipes.data.entities.Category
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CategoriesListFragment : Fragment(), OnCategoryItemLongClick {

    private val mCategoriesListViewModel: CategoriesListViewModel by viewModels()

    private val adapter: CategoriesListAdapter by lazy { CategoriesListAdapter() }

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories_list, container, false)

        findViewsById(view)
        setupViews()
        initViewModels()

        setHasOptionsMenu(true)

        return view
    }

    private fun findViewsById(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        fab = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
    }

    private fun setupViews() {
        adapter.setOnClickListener(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesListFragment_to_addOrEditCategoryFragment)
        }
    }

    private fun initViewModels() {
        mCategoriesListViewModel.getAllCategories.observe(viewLifecycleOwner, Observer { data ->
            mCategoriesListViewModel.checkIfDataBaseEmpty(data)
            adapter.setData(data)

            //mCategoriesListViewModel.checkIfDataBaseEmpty(getCategories(true))
            //adapter.setData(getCategories(true))

            //mCategoriesListViewModel.checkIfDataBaseEmpty(getCategories(false))
            //adapter.setData(getCategories(false))
        })

        mCategoriesListViewModel.emptyDataBase.observe(viewLifecycleOwner, {
            showEmptyDataBaseViews(it)
        })
    }

    private fun showEmptyDataBaseViews(emptyDataBase: Boolean) {
        val no_data_img = view?.findViewById<ImageView>(R.id.no_data_img)
        val no_data_txt = view?.findViewById<TextView>(R.id.no_data_txt)

        if (emptyDataBase) {
            no_data_img?.visibility = View.VISIBLE
            no_data_txt?.visibility = View.VISIBLE
        } else {
            no_data_img?.visibility = View.INVISIBLE
            no_data_txt?.visibility = View.INVISIBLE
        }
    }

    override fun editCategory(category: Category) {

    }

    override fun deleteCategory(category: Category) {
        AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.delete_category_dialog_title))
            //.setTitle("Важное сообщение! Пожалуйста, прочитайте его! Очень прошу!")
            //.setCustomTitle(textView)
            //.setIcon(R.drawable.hungrycat)
            .setPositiveButton(
                R.string.btn_ok,
                { dialogInterface, i ->
                    mCategoriesListViewModel.deleteCategory(category)
                })
            .setNegativeButton(
                R.string.btn_cancel,
                { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
            .create()
            .show()
    }
}


/*
override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
inflater.inflate(R.menu.list_fragment_menu, menu)

val search = menu.findItem(R.id.menu_search)
val searchView = search.actionView as? SearchView
searchView?.setQueryHint("Search View Hint")
searchView?.isSubmitButtonEnabled = true
searchView?.setOnQueryTextListener(this)
}*/


/*




override fun onOptionsItemSelected(item: MenuItem): Boolean {

when (item.itemId) {
R.id.menu_delete_all -> confirmAllRemoval()
R.id.menu_priority_high -> mToDoViewModel.sortByHighPriority.observe(this, Observer {
    adapter.setData(it)
})
R.id.menu_priority_low -> mToDoViewModel.sortByLowPriority.observe(this, Observer {
    adapter.setData(it)
})

}

return super.onOptionsItemSelected(item)
}

override fun onQueryTextSubmit(query: String?): Boolean {
if (query != null) {
searchThroughDataBase(query)
}
return true
}

override fun onQueryTextChange(query: String?): Boolean {
if (query != null) {
searchThroughDataBase(query)
}
return true
}

private fun searchThroughDataBase(query: String) {
val searchQuery = "%$query%"

mToDoViewModel.searchDataBase(searchQuery).observe(this, Observer() { list ->
list?.let {
    adapter.setData(it)
}
})

}

private fun confirmAllRemoval() {
val builder = AlertDialog.Builder(requireContext())

builder.setTitle("Delete everything?")
builder.setMessage("Are you sure you want to remove everything?")

builder.setPositiveButton("Yes") { _, _ ->
mToDoViewModel.deleteAll()
Toast.makeText(
    requireContext(),
    "Successfully removed everything!",
    Toast.LENGTH_LONG
).show()
}

builder.setNegativeButton("No") { _, _ -> }

builder.create().show()
}

private fun swipeToDelete(recyclerView: RecyclerView) {
val swipeToDeleteCallback = object : SwipeToDelete() {
override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    val deletedItem = adapter.dataList[viewHolder.adapterPosition]

    mToDoViewModel.deleteItem(deletedItem)
    adapter.notifyItemRemoved(viewHolder.adapterPosition)

    restoreDeletedItem(viewHolder.itemView, deletedItem)
}
}

val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
itemTouchHelper.attachToRecyclerView(recyclerView)
}

private fun restoreDeletedItem(view: View, deletedItem: ToDoData) {
val snackBar = Snackbar.make(
view,
"Deleted item '${deletedItem.title}'",
Snackbar.LENGTH_LONG
)

snackBar.setAction("Undo") {
mToDoViewModel.insertData(deletedItem)
}

snackBar.show()
}*/