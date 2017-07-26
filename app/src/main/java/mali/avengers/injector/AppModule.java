/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package mali.avengers.injector;

import javax.inject.Singleton;

import mali.avengers.AvengersApplication;
import mali.avengers.model.repository.Repository;
import mali.avengers.model.rest.RestDataSource;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final AvengersApplication mAvengersApplication;

    public AppModule(AvengersApplication avengersApplication) {

        this.mAvengersApplication = avengersApplication;
    }

    @Provides @Singleton AvengersApplication provideAvengersApplicationContext () { return mAvengersApplication; }

    @Provides @Singleton Repository provideDataRepository (RestDataSource restDataSource) { return restDataSource; }
}
