package com.baz.simaht.injection.module;

import android.app.Application;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetworkModule_ProvideApplication$app_debugFactory
    implements Factory<Application> {
  private final Provider<Context> contextProvider;

  public NetworkModule_ProvideApplication$app_debugFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public Application get() {
    return Preconditions.checkNotNull(
        NetworkModule.provideApplication$app_debug(contextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static NetworkModule_ProvideApplication$app_debugFactory create(
      Provider<Context> contextProvider) {
    return new NetworkModule_ProvideApplication$app_debugFactory(contextProvider);
  }

  public static Application proxyProvideApplication$app_debug(Context context) {
    return Preconditions.checkNotNull(
        NetworkModule.provideApplication$app_debug(context),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
