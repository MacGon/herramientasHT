package com.baz.simaht.injection.module;

import android.content.Context;
import com.baz.simaht.base.BaseView;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetworkModule_ProvideContext$app_debugFactory implements Factory<Context> {
  private final Provider<BaseView> baseViewProvider;

  public NetworkModule_ProvideContext$app_debugFactory(Provider<BaseView> baseViewProvider) {
    this.baseViewProvider = baseViewProvider;
  }

  @Override
  public Context get() {
    return Preconditions.checkNotNull(
        NetworkModule.provideContext$app_debug(baseViewProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static NetworkModule_ProvideContext$app_debugFactory create(
      Provider<BaseView> baseViewProvider) {
    return new NetworkModule_ProvideContext$app_debugFactory(baseViewProvider);
  }

  public static Context proxyProvideContext$app_debug(BaseView baseView) {
    return Preconditions.checkNotNull(
        NetworkModule.provideContext$app_debug(baseView),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
