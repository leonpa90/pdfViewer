package com.example.gallery.view

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.R
import com.example.gallery.adapter.HomeAdapter
import com.example.gallery.utils.BitmapUtils
import com.example.gallery.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {
    val adapter = HomeAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: HomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        recycle_view.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getPdfList()
        recycle_view.adapter = adapter
        enableSwipe()
        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = false
            viewModel.getPdfList()

        }

        viewModel.pdfData.observe(viewLifecycleOwner) {
            adapter.pdfList = it.content.toMutableList()

        }


        viewModel.errodata.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, "C'è stato un errore!", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.onItemClick = {
            view.findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundleOf("pdf" to adapter.pdfList[it])
            )

        }
    }

    private fun enableSwipe() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition

                    if (direction == ItemTouchHelper.LEFT) {
                        adapter.removeItem(position)
                        val snackbar = Snackbar.make(
                            containerLayout,
                            " Elemento Rimosso",
                            Snackbar.LENGTH_LONG
                        )

                        snackbar.setActionTextColor(Color.YELLOW)
                        snackbar.show()
                    }
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    val p = Paint()
                    p.color = Color.parseColor("#D32F2F")

                    val icon: Bitmap
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                        val itemView = viewHolder.itemView
                        val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                        val width = height / 3

                        if (dX > 0) {
                            val background =
                                RectF(
                                    itemView.left.toFloat(),
                                    itemView.top.toFloat(),
                                    dX,
                                    itemView.bottom.toFloat()
                                )
                            c.drawRect(background, p)
                            icon = BitmapUtils.getBitmap(requireContext(), R.drawable.delete)!!
                            val icon_dest = RectF(
                                itemView.left.toFloat() + width,
                                itemView.top.toFloat() + width,
                                itemView.left.toFloat() + 2 * width,
                                itemView.bottom.toFloat() - width
                            )
                            c.drawBitmap(icon, null, icon_dest, p)
                        } else {
                            val background = RectF(
                                itemView.right.toFloat() + dX,
                                itemView.top.toFloat(),
                                itemView.right.toFloat(),
                                itemView.bottom.toFloat()
                            )
                            c.drawRect(background, p)
                            icon = BitmapUtils.getBitmap(requireContext(), R.drawable.delete)!!
                            val icon_dest = RectF(
                                itemView.right.toFloat() - 2 * width,
                                itemView.top.toFloat() + width,
                                itemView.right.toFloat() - width,
                                itemView.bottom.toFloat() - width
                            )
                            c.drawBitmap(icon, null, icon_dest, p)
                        }
                    }
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recycle_view)
    }

}