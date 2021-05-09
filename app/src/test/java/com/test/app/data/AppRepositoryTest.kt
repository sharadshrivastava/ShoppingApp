package com.test.app.data

import com.test.app.BaseTest
import com.test.app.data.network.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppRepositoryTest : BaseTest() {

    @Test
    fun testSuccessResponse() {
        setResponse("response.json")
        runBlocking {
            Assert.assertTrue(
                repository.products().status == Resource.Status.SUCCESS
            )
        }
    }

    @Test
    fun testFailResponse() {
        setErrorResponse()
        runBlocking {
            Assert.assertTrue(
                repository.products().status == Resource.Status.ERROR
            )
        }
    }

    @Test
    fun testEmployeeItems() {
        setResponse("response.json")
        runBlocking {
            val expectedItems = 11 //in local json file, we have 11 employee items.
            Assert.assertTrue(
                repository.products().data?.size == expectedItems
            )
        }
    }

    //In this way we can test other functionality as well, using mock webserver and dummy responses.
}