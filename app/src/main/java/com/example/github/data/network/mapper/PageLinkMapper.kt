package com.example.github.data.network.mapper

import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.model.PaginationInfo
import com.example.github.data.network.tools.PageLinks
import javax.inject.Inject

class PageLinkMapper @Inject constructor() : Mapper<PageLinks, PaginationInfo> {

    override fun map(input: PageLinks): PaginationInfo {
        return PaginationInfo(input.first, input.next,input.prev)
    }

}
