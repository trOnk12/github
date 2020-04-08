package com.example.github.data.network.service

import com.example.github.core.functional.mapper.Mapper
import com.example.github.data.network.model.NextPageInfo
import com.example.github.data.network.tools.PageLinks
import javax.inject.Inject

class PageLinksMapper @Inject constructor() : Mapper<PageLinks, NextPageInfo> {

    override fun map(input: PageLinks): NextPageInfo {
        return NextPageInfo(input.first, input.next,input.prev)
    }

}
