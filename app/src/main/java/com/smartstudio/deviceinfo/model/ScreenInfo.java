/*
 * Copyright 2016 Smart Studio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartstudio.deviceinfo.model;

/**
 * Represents information about the device screen like resolution, density, etc
 **/
public class ScreenInfo {
    private String deviceModel;
    private String manufacturer;
    private int widthPixels;
    private int heightPixels;
    private double inches;
    private int statusBarHeight;
    private int navigationBarHeight;
    private String screenSize;
    private int densityDpi;
    private double density;
    private String densityCode;
    private double densityX;
    private double densityY;
    private int actionBarHeight;
    private int contentTop;
    private int contentBottom;
    private int contentHeight;

    public ScreenInfo() {

    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getWidthPixels() {
        return widthPixels;
    }

    public void setWidthPixels(int widthPixels) {
        this.widthPixels = widthPixels;
    }

    public int getHeightPixels() {
        return heightPixels;
    }

    public void setHeightPixels(int heightPixels) {
        this.heightPixels = heightPixels;
    }

    public double getInches() {
        return inches;
    }

    public void setInches(double inches) {
        this.inches = inches;
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    public void setStatusBarHeight(int statusBarHeight) {
        this.statusBarHeight = statusBarHeight;
    }

    public int getNavigationBarHeight() {
        return navigationBarHeight;
    }

    public void setNavigationBarHeight(int navigationBarHeight) {
        this.navigationBarHeight = navigationBarHeight;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public int getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(int densityDpi) {
        this.densityDpi = densityDpi;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public String getDensityCode() {
        return densityCode;
    }

    public void setDensityCode(String densityCode) {
        this.densityCode = densityCode;
    }

    public double getDensityX() {
        return densityX;
    }

    public void setDensityX(double densityX) {
        this.densityX = densityX;
    }

    public double getDensityY() {
        return densityY;
    }

    public void setDensityY(double densityY) {
        this.densityY = densityY;
    }

    public int getActionBarHeight() {
        return actionBarHeight;
    }

    public void setActionBarHeight(int actionBarHeight) {
        this.actionBarHeight = actionBarHeight;
    }

    public int getContentTop() {
        return contentTop;
    }

    public void setContentTop(int contentTop) {
        this.contentTop = contentTop;
    }

    public int getContentBottom() {
        return contentBottom;
    }

    public void setContentBottom(int contentBottom) {
        this.contentBottom = contentBottom;
    }

    public int getContentHeight() {
        return contentHeight;
    }

    public void setContentHeight(int contentHeight) {
        this.contentHeight = contentHeight;
    }
}
