/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers;

import android.app.Application;

import mali.avengers.injector.AppModule;
import mali.avengers.injector.components.AppComponent;
import mali.avengers.injector.components.DaggerAppComponent;

public class AvengersApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {

        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {

        mAppComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }
}
