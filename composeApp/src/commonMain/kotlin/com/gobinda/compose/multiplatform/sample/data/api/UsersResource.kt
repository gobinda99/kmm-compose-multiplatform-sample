package com.gobinda.compose.multiplatform.sample.data.api

import io.ktor.resources.Resource

@Resource("api/")
class UserResource() {
    @Resource("new")
    class New(val parent: UserResource = UserResource())

    @Resource("{id}")
    class Id(val parent: UserResource = UserResource(), val id: Long) {
        @Resource("edit")
        class Edit(val parent: Id)
    }


}