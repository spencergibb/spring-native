package org.springframework.boot.diagnostics;

import java.util.List;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.nativex.substitutions.OnlyIfPresent;
import org.springframework.nativex.substitutions.WithAot;

@TargetClass(className="org.springframework.boot.diagnostics.FailureAnalyzers", onlyWith = { WithAot.class, OnlyIfPresent.class })
final class Target_FailureAnalyzers {

	@Substitute
	private List<FailureAnalyzer> loadFailureAnalyzers(ConfigurableApplicationContext context,
			ClassLoader classLoader) {
		// TODO: analyzers are not ordered
		return SpringFactoriesLoader.loadFactories(FailureAnalyzer.class, classLoader);
	}

}
