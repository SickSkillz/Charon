/*
 * This file is part of Charon.
 *
 * Charon is a library that extends the SpigotMC YAML Configuration API
 * Copyright (C) 2023  SickSkillz
 *
 * Charon is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Charon is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Charon.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.sickskillz.charon.validators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public abstract class Validator {

    @Getter
    @Setter
    protected @Nullable BiFunction<String, Object, Void> errorCallback;

    protected abstract boolean isValueValid(Object value);

    public boolean isValid(String path, Object value) {
        if (isValueValid(value)) {
            return true;
        }

        if (errorCallback != null) {
            errorCallback.apply(path, value);
        }

        return false;
    }
}
