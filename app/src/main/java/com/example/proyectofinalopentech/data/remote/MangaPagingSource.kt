package com.example.proyectofinalopentech.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.proyectofinalopentech.data.model.MangaDto
import com.example.proyectofinalopentech.data.remote.interfaces.RemoteDataSource


const val NETWORK_PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE = 1

class MangaPagingSource(
    private val mangaName:String,
    private val service: RemoteDataSource,
) : PagingSource<Int, MangaDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MangaDto> {
        // Start refresh at position 1 if undefined.
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        return try {
            val responseDTO = service.getMangasByName(mangaName = mangaName ,offset = offset, limit = params.loadSize).data

            val nextKey = if (responseDTO.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = responseDTO,
                prevKey = null, // Only paging forward.
                // assume that if a full page is not loaded, that means the end of the data
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MangaDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)}
    }
}
