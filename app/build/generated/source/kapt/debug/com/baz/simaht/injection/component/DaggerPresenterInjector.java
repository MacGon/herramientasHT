package com.baz.simaht.injection.component;

import com.baz.simaht.base.BaseView;
import com.baz.simaht.injection.module.ContextModule;
import com.baz.simaht.injection.module.NetworkModule;
import com.baz.simaht.login.presenter.LoginPresenterImpl;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerPresenterInjector implements PresenterInjector {
  private DaggerPresenterInjector(Builder builder) {}

  public static PresenterInjector.Builder builder() {
    return new Builder();
  }

  @Override
  public void inject(LoginPresenterImpl mLoginPresenterImpl) {}

  private static final class Builder implements PresenterInjector.Builder {
    private BaseView baseView;

    @Override
    public PresenterInjector build() {
      if (baseView == null) {
        throw new IllegalStateException(BaseView.class.getCanonicalName() + " must be set");
      }
      return new DaggerPresenterInjector(this);
    }

    /**
     * This module is declared, but an instance is not used in the component. This method is a
     * no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Override
    public Builder networkModule(NetworkModule networkModule) {
      return this;
    }

    /**
     * This module is declared, but an instance is not used in the component. This method is a
     * no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Override
    public Builder contextModule(ContextModule contextModule) {
      return this;
    }

    @Override
    public Builder baseView(BaseView baseView) {
      this.baseView = Preconditions.checkNotNull(baseView);
      return this;
    }
  }
}
