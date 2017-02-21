package ru.edustor.toolchain.cpmm.util

import org.junit.Assert
import org.junit.Test
import ru.edustor.toolchain.cpmm.model.pg.Tag

class TagUtilsTest {
    @Test
    fun testTagPath() {
        var tag: Tag? = null

        for (id in arrayOf("hello", "brave", "new", "world")) {
            val childTag = Tag()
            childTag.id = id
            childTag.parent = tag
            tag = childTag
        }

        Assert.assertEquals("/hello/brave/new/", tag!!.path)
        Assert.assertEquals("world", tag.id)
    }
}