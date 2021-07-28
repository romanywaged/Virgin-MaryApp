package  com.example.vergionmaryapp.dagger.component

import com.example.vergionmaryapp.booking.showEvents.EventListViewActivity
import com.example.vergionmaryapp.dagger.module.AppModule
import com.example.vergionmaryapp.dagger.module.NetworkModule
import javax.inject.Singleton
import dagger.Component

/**
 * Created by Marina on 30/6/2019.
 */

@Singleton
@Component(modules = [(NetworkModule::class), (AppModule::class)])
interface NetworkComponent
{

//Display EventsByCategoryId Module
    fun inject(elNahdaActivity: EventListViewActivity)

}