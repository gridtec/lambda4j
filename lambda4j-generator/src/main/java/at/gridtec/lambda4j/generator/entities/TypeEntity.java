/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.gridtec.lambda4j.generator.entities;

import java.io.Serializable;

/**
 * Created by domin on 05.02.2016.
 */
public class TypeEntity implements Serializable {

    private Class<?> typeClass;

    private String typeName;

    private boolean isPrimitive;

    public TypeEntity() {

    }

    public TypeEntity(final Class<?> typeClass, final String typeName) {
        this.typeClass = typeClass;
        this.typeName = typeName;
        this.isPrimitive = typeClass.isPrimitive();
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(Class<?> typeClass) {
        this.typeClass = typeClass;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public void setPrimitive(boolean primitive) {
        isPrimitive = primitive;
    }

    //    @Override
    //    public boolean equals(Object o) {
    //        if (this == o) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //
    //        TypeEntity entity = (TypeEntity) o;
    //
    //        if (isPrimitive != entity.isPrimitive) {
    //            return false;
    //        }
    //        if (!typeClass.equals(entity.typeClass)) {
    //            return false;
    //        }
    //        return typeName.equals(entity.typeName);
    //
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        int result = typeClass.hashCode();
    //        result = 31 * result + typeName.hashCode();
    //        result = 31 * result + (isPrimitive ? 1 : 0);
    //        return result;
    //    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeEntity entity = (TypeEntity) o;

        return typeClass.equals(entity.typeClass);

    }

    @Override
    public int hashCode() {
        return typeClass.hashCode();
    }

    @Override
    public String toString() {
        return "TypeEntity{" +
                "typeClass=" + typeClass +
                ", typeName='" + typeName + '\'' +
                ", isPrimitive=" + isPrimitive +
                '}';
    }
}