package com.simaht.utils

private class SingletonClass {

    private var sSoleInstance: SingletonClass? = null


    init {
        if (sSoleInstance != null){
            throw RuntimeException("Use getInstance() method to get the single instance of this class.")
        }
    }


    fun getInstance(): SingletonClass? {
        //Double check locking pattern
        if (sSoleInstance == null) { //Check for the first time

            synchronized(SingletonClass::class.java) {
                //Check for the second time.
                //if there is no instance available... create new one
                if (sSoleInstance == null) sSoleInstance = SingletonClass()
            }
        }

        return sSoleInstance
    }

    companion object {
        @Volatile
        private var sSoleInstance = SingletonClass()
    }

}