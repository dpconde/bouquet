package com.dpconde.bouquet.di.module.view.tasks;

import android.content.Context;

import com.dpconde.bouquet.di.module.ContextModule;
import com.dpconde.bouquet.di.module.DataProviderModule;
import com.dpconde.bouquet.mvp.data.api.TestDataManager;
import com.dpconde.bouquet.mvp.view.task.misuse.MisusePresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dpconde on 29/9/18.
 */

@Module(includes = {DataProviderModule.class, ContextModule.class})
public class MisuseTaskModule {

    private MisusePresenter.View view;

    public MisuseTaskModule(MisusePresenter.View view) {
        this.view = view;
    }

    @Provides
    public MisusePresenter.View provideView() {
        return view;
    }

    @Provides
    public MisusePresenter providePresenter(MisusePresenter.View categoryView,
                                              @Named("db") TestDataManager dataManager,
                                              Context context) {
        return new MisusePresenter(categoryView, context, dataManager);
    }

}
