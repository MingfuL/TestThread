/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.liuxiaoming.upwork;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public final class UpData {

    public static final UpData EMPTY = new UpData.Builder().build();
    public static final int MAX_DATA_BYTES = 10 * 1024;    // 10KB

    private static final String TAG = "UpData";

    @SuppressWarnings("WeakerAccess") /* synthetic access */
            Map<String, Object> mValues;

    UpData() {    // stub required for room
    }

    UpData(Map<String, ?> values) {
        mValues = new HashMap<>(values);
    }


    public Object getObject(@NonNull String key){
        Object value = mValues.get(key);
        return value;
    }

    /**
     * Get the boolean value for the given key.
     *
     * @param key          The key for the argument
     * @param defaultValue The default value to return if the key is not found
     * @return The value specified by the key if it exists; the default value otherwise
     */
    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        Object value = mValues.get(key);
        if (value instanceof Boolean) {
            return (boolean) value;
        } else {
            return defaultValue;
        }
    }

    /**
     * Get the boolean array value for the given key.
     *
     * @param key The key for the argument
     * @return The value specified by the key if it exists; {@code null} otherwise
     */
    public @NonNull
    boolean[] getBooleanArray(@NonNull String key) {
        Object value = mValues.get(key);
        if (value instanceof Boolean[]) {
            Boolean[] array = (Boolean[]) value;
            boolean[] returnArray = new boolean[array.length];
            for (int i = 0; i < array.length; ++i) {
                returnArray[i] = array[i];
            }
            return returnArray;
        } else {
            return null;
        }
    }


    /**
     * Get the integer value for the given key.
     *
     * @param key          The key for the argument
     * @param defaultValue The default value to return if the key is not found
     * @return The value specified by the key if it exists; the default value otherwise
     */
    public int getInt(@NonNull String key, int defaultValue) {
        Object value = mValues.get(key);
        if (value instanceof Integer) {
            return (int) value;
        } else {
            return defaultValue;
        }
    }

    /**
     * Get the integer array value for the given key.
     *
     * @param key The key for the argument
     * @return The value specified by the key if it exists; {@code null} otherwise
     */
    public @NonNull
    int[] getIntArray(@NonNull String key) {
        Object value = mValues.get(key);
        if (value instanceof Integer[]) {
            Integer[] array = (Integer[]) value;
            int[] returnArray = new int[array.length];
            for (int i = 0; i < array.length; ++i) {
                returnArray[i] = array[i];
            }
            return returnArray;
        } else {
            return null;
        }
    }

    /**
     * Get the long value for the given key.
     *
     * @param key          The key for the argument
     * @param defaultValue The default value to return if the key is not found
     * @return The value specified by the key if it exists; the default value otherwise
     */
    public long getLong(@NonNull String key, long defaultValue) {
        Object value = mValues.get(key);
        if (value instanceof Long) {
            return (long) value;
        } else {
            return defaultValue;
        }
    }

    /**
     * Get the long array value for the given key.
     *
     * @param key The key for the argument
     * @return The value specified by the key if it exists; {@code null} otherwise
     */
    public @Nullable
    long[] getLongArray(@NonNull String key) {
        Object value = mValues.get(key);
        if (value instanceof Long[]) {
            Long[] array = (Long[]) value;
            long[] returnArray = new long[array.length];
            for (int i = 0; i < array.length; ++i) {
                returnArray[i] = array[i];
            }
            return returnArray;
        } else {
            return null;
        }
    }

    /**
     * Get the float value for the given key.
     *
     * @param key          The key for the argument
     * @param defaultValue The default value to return if the key is not found
     * @return The value specified by the key if it exists; the default value otherwise
     */
    public float getFloat(@NonNull String key, float defaultValue) {
        Object value = mValues.get(key);
        if (value instanceof Float) {
            return (float) value;
        } else {
            return defaultValue;
        }
    }

    /**
     * Get the float array value for the given key.
     *
     * @param key The key for the argument
     * @return The value specified by the key if it exists; {@code null} otherwise
     */
    public @Nullable
    float[] getFloatArray(@NonNull String key) {
        Object value = mValues.get(key);
        if (value instanceof Float[]) {
            Float[] array = (Float[]) value;
            float[] returnArray = new float[array.length];
            for (int i = 0; i < array.length; ++i) {
                returnArray[i] = array[i];
            }
            return returnArray;
        } else {
            return null;
        }
    }

    /**
     * Get the double value for the given key.
     *
     * @param key          The key for the argument
     * @param defaultValue The default value to return if the key is not found
     * @return The value specified by the key if it exists; the default value otherwise
     */
    public double getDouble(@NonNull String key, double defaultValue) {
        Object value = mValues.get(key);
        if (value instanceof Double) {
            return (double) value;
        } else {
            return defaultValue;
        }
    }

    /**
     * Get the double array value for the given key.
     *
     * @param key The key for the argument
     * @return The value specified by the key if it exists; {@code null} otherwise
     */
    public @Nullable
    double[] getDoubleArray(@NonNull String key) {
        Object value = mValues.get(key);
        if (value instanceof Double[]) {
            Double[] array = (Double[]) value;
            double[] returnArray = new double[array.length];
            for (int i = 0; i < array.length; ++i) {
                returnArray[i] = array[i];
            }
            return returnArray;
        } else {
            return null;
        }
    }

    /**
     * Get the String value for the given key.
     *
     * @param key          The key for the argument
     * @param defaultValue The default value to return if the key is not found
     * @return The value specified by the key if it exists; the default value otherwise
     */
    public @Nullable
    String getString(@NonNull String key, @Nullable String defaultValue) {
        Object value = mValues.get(key);
        if (value instanceof String) {
            return (String) value;
        } else {
            return defaultValue;
        }
    }

    /**
     * Get the String array value for the given key.
     *
     * @param key The key for the argument
     * @return The value specified by the key if it exists; {@code null} otherwise
     */
    public @Nullable
    String[] getStringArray(@NonNull String key) {
        Object value = mValues.get(key);
        if (value instanceof String[]) {
            return (String[]) value;
        } else {
            return null;
        }
    }

    /**
     * Gets all the values in this UpData object.
     *
     * @return A {@link Map} of key-value pairs for this object; this Map is unmodifiable and should
     * be used for reads only.
     */
    public @NonNull
    Map<String, Object> getKeyValueMap() {
        return Collections.unmodifiableMap(mValues);
    }

    /**
     * @return The number of arguments
     */
    @VisibleForTesting
    public int size() {
        return mValues.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpData other = (UpData) o;
        return mValues.equals(other.mValues);
    }

    @Override
    public int hashCode() {
        return 31 * mValues.hashCode();
    }

    @SuppressWarnings("WeakerAccess") /* synthetic access */
    static Boolean[] convertPrimitiveBooleanArray(boolean[] value) {
        Boolean[] returnValue = new Boolean[value.length];
        for (int i = 0; i < value.length; ++i) {
            returnValue[i] = value[i];
        }
        return returnValue;
    }

    @SuppressWarnings("WeakerAccess") /* synthetic access */
    static Integer[] convertPrimitiveIntArray(int[] value) {
        Integer[] returnValue = new Integer[value.length];
        for (int i = 0; i < value.length; ++i) {
            returnValue[i] = value[i];
        }
        return returnValue;
    }

    @SuppressWarnings("WeakerAccess") /* synthetic access */
    static Long[] convertPrimitiveLongArray(long[] value) {
        Long[] returnValue = new Long[value.length];
        for (int i = 0; i < value.length; ++i) {
            returnValue[i] = value[i];
        }
        return returnValue;
    }

    @SuppressWarnings("WeakerAccess") /* synthetic access */
    static Float[] convertPrimitiveFloatArray(float[] value) {
        Float[] returnValue = new Float[value.length];
        for (int i = 0; i < value.length; ++i) {
            returnValue[i] = value[i];
        }
        return returnValue;
    }

    @SuppressWarnings("WeakerAccess") /* synthetic access */
    static Double[] convertPrimitiveDoubleArray(double[] value) {
        Double[] returnValue = new Double[value.length];
        for (int i = 0; i < value.length; ++i) {
            returnValue[i] = value[i];
        }
        return returnValue;
    }

    /**
     * A builder for {@link UpData}.
     */
    public static final class Builder {

        private Map<String, Object> mValues = new HashMap<>();

        /**
         * Puts a Object into the arguments.
         *
         * @param key The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putObject(@NonNull String key, Object value){
            mValues.put(key, value);
            return this;
        }

        /**
         * Puts a boolean into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putBoolean(@NonNull String key, boolean value) {
            mValues.put(key, value);
            return this;
        }

        /**
         * Puts a boolean array into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putBooleanArray(@NonNull String key, boolean[] value) {
            mValues.put(key, convertPrimitiveBooleanArray(value));
            return this;
        }

        /**
         * Puts an integer into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putInt(@NonNull String key, int value) {
            mValues.put(key, value);
            return this;
        }

        /**
         * Puts an integer array into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putIntArray(@NonNull String key, int[] value) {
            mValues.put(key, convertPrimitiveIntArray(value));
            return this;
        }

        /**
         * Puts a long into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putLong(@NonNull String key, long value) {
            mValues.put(key, value);
            return this;
        }

        /**
         * Puts a long array into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putLongArray(@NonNull String key, long[] value) {
            mValues.put(key, convertPrimitiveLongArray(value));
            return this;
        }

        /**
         * Puts a float into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putFloat(@NonNull String key, float value) {
            mValues.put(key, value);
            return this;
        }

        /**
         * Puts a float array into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putFloatArray(String key, float[] value) {
            mValues.put(key, convertPrimitiveFloatArray(value));
            return this;
        }

        /**
         * Puts a double into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putDouble(@NonNull String key, double value) {
            mValues.put(key, value);
            return this;
        }

        /**
         * Puts a double array into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putDoubleArray(@NonNull String key, double[] value) {
            mValues.put(key, convertPrimitiveDoubleArray(value));
            return this;
        }

        /**
         * Puts a String into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putString(@NonNull String key, String value) {
            mValues.put(key, value);
            return this;
        }

        /**
         * Puts a String array into the arguments.
         *
         * @param key   The key for this argument
         * @param value The value for this argument
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putStringArray(@NonNull String key, String[] value) {
            mValues.put(key, value);
            return this;
        }

        /**
         * Puts all input key-value pairs from the {@link UpData} into the Builder.
         * Any non-valid types will be logged and ignored.  Valid types are: Boolean, Integer,
         * Long, Double, String, and array versions of each of those types.
         * Any {@code null} values will also be ignored.
         *
         * @param data {@link UpData} containing key-value pairs to add
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putAll(@NonNull UpData data) {
            putAll(data.mValues);
            return this;
        }

        /**
         * Puts all input key-value pairs into the Builder. Valid types are: Boolean, Integer,
         * Long, Float, Double, String, and array versions of each of those types.
         * Invalid types throw an {@link IllegalArgumentException}.
         *
         * @param values A {@link Map} of key-value pairs to add
         * @return The {@link Builder}
         */
        public @NonNull
        Builder putAll(@NonNull Map<String, Object> values) {
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    mValues.put(key, null);
                    continue;
                }
                Class valueType = value.getClass();
                if (valueType == Boolean.class
                        || valueType == Integer.class
                        || valueType == Long.class
                        || valueType == Float.class
                        || valueType == Double.class
                        || valueType == String.class
                        || valueType == Boolean[].class
                        || valueType == Integer[].class
                        || valueType == Long[].class
                        || valueType == Float[].class
                        || valueType == Double[].class
                        || valueType == String[].class) {
                    mValues.put(key, value);
                } else if (valueType == boolean[].class) {
                    mValues.put(key, convertPrimitiveBooleanArray((boolean[]) value));
                } else if (valueType == int[].class) {
                    mValues.put(key, convertPrimitiveIntArray((int[]) value));
                } else if (valueType == long[].class) {
                    mValues.put(key, convertPrimitiveLongArray((long[]) value));
                } else if (valueType == float[].class) {
                    mValues.put(key, convertPrimitiveFloatArray((float[]) value));
                } else if (valueType == double[].class) {
                    mValues.put(key, convertPrimitiveDoubleArray((double[]) value));
                } else {
                    throw new IllegalArgumentException(
                            String.format("Key %s has invalid type %s", key, valueType));
                }
            }
            return this;
        }

        /**
         * Builds an {@link UpData} object.
         *
         * @return The {@link UpData} object containing all key-value pairs specified by this
         * {@link Builder}.
         */
        public @NonNull
        UpData build() {
            return new UpData(mValues);
        }
    }
}
