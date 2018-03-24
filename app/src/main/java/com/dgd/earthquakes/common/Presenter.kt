package com.dgd.earthquakes.common

import com.dgd.earthquakes.BaseApplication
import com.dgd.earthquakes.data.IRepo

/**
 * Created by max
 * on 04/10/17.
 */

open class Presenter<T>(protected val view: T) : ScreenContract.IPresenter {

    protected val repo: IRepo
        get() = BaseApplication.instance?.repository!!
}
