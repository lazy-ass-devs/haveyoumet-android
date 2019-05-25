package com.lazyassevs.haveyoumet.ui.view.album.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lazyassevs.domain.model.Album
import com.lazyassevs.haveyoumet.R
import com.lazyassevs.haveyoumet.databinding.FragmentAlbumListBinding
import com.lazyassevs.haveyoumet.databinding.ItemAlbumBinding
import com.lazyassevs.haveyoumet.util.base.BaseFragment
import com.lazyassevs.haveyoumet.util.base.SimpleListAdapter
import com.lazyassevs.haveyoumet.util.diffcallback.AlbumItemCallback
import com.lazyassevs.haveyoumet.util.extensions.observe
import com.lazyassevs.haveyoumet.util.extensions.withBinding
import com.lazyassevs.haveyoumet.util.extensions.withViewModel
import timber.log.Timber

class AlbumListFragment : BaseFragment() {

    private lateinit var binding: FragmentAlbumListBinding
    private lateinit var viewModel: AlbumListViewModel

    private val albumListAdapter = object : SimpleListAdapter<Album, ItemAlbumBinding>(AlbumItemCallback) {
        override fun bind(holder: ViewHolder<ItemAlbumBinding>, item: Album) {
            holder.binding.album = item
            holder.itemView.setOnClickListener { onAlbumClick(item) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = withBinding(inflater, R.layout.fragment_album_list, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = withViewModel(this, viewModelFactory) {
            observe(observableState, ::render)
            dispatch(Action.GetAlbums)
        }
        binding.apply {
            setupRecyclerView(recyclerView)
            fabAdd.setOnClickListener { onAddAlbum() }
            swipeRefreshLayout.setOnRefreshListener { viewModel.dispatch(Action.FetchAlbums) }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AlbumListFragment.context)
            adapter = albumListAdapter
        }
    }

    // todo
    private fun onAddAlbum() {
        Timber.d("onAddAlbum")
    }

    // todo
    private fun onAlbumClick(album: Album) {
        Timber.d("onAlbumClick: $album")
    }

    private fun render(state: State) {
        binding.state = state
        albumListAdapter.submitList(state.albums)
        if (state.error != null) {
            Toast.makeText(context, state.error.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

}