/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aot;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author Brian Clozel
 */
public abstract class ResourceFiles {

	/**
	 * Create a {@link ResourceFile}, given a {@link Path} of an existing file
	 * and an optional relative path showing where the resource should be written under
	 * {@code "src/main/resources"}.
	 * @param sourcePath the source path
	 * @param relativeTo the relative path
	 * @return the resource file
	 */
	public static ResourceFile fromStaticFile(Path sourcePath, Path relativeTo) {
		return rootPath -> {
			Path resourcePath = rootPath.resolve(ResourceFile.MAIN_RESOURCES_PATH);
			if (relativeTo != null) {
				resourcePath = resourcePath.resolve(relativeTo);
			}
			Files.createDirectories(resourcePath);
			resourcePath = resourcePath.resolve(sourcePath.getFileName());
			Files.copy(sourcePath, resourcePath, StandardCopyOption.REPLACE_EXISTING);
		};
	}

}
