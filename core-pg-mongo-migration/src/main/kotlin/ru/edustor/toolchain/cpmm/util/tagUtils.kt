package ru.edustor.toolchain.cpmm.util

import ru.edustor.toolchain.cpmm.model.pg.Tag

val Tag.path: String
    get() {
        if (this.parent == null) {
            return "/"
        } else {
            return "${parent!!.path}${parent!!.id}/"
        }
    }