package com.android.project3.model;

/**
 * Created by keban on 5/20/2017.
 */

public class Warranty {

    private Builder builder;

    public Warranty(Builder builder)
    {
        this.builder = builder;
    }

    public Builder getBuilder()
    {
        return builder;
    }

    public static class Builder
    {
        private int dauNhon;
        private int dauGiamXoc;
        private int dauHopSo;
        private int dauPhanh;

        private int lopTruoc;
        private int lopSau;

        private int acquy;
        private int xich;

        private int phanh;
        private int phanhDau;

        private int bugi;
        private int tamLocGio;
        private int nuocLamMat;

        public int getDauNhon() {
            return dauNhon;
        }

        public Builder setDauNhon(int dauNhon) {
            this.dauNhon = dauNhon;
            return this;
        }

        public int getDauGiamXoc() {
            return dauGiamXoc;
        }

        public Builder setDauGiamXoc(int dauGiamXoc) {
            this.dauGiamXoc = dauGiamXoc;
            return this;
        }

        public int getDauHopSo() {
            return dauHopSo;
        }

        public Builder setDauHopSo(int dauHopSo) {
            this.dauHopSo = dauHopSo;
            return this;
        }

        public int getDauPhanh() {
            return dauPhanh;
        }

        public Builder setDauPhanh(int dauPhanh) {
            this.dauPhanh = dauPhanh;
            return this;
        }

        public int getLopTruoc() {
            return lopTruoc;
        }

        public Builder setLopTruoc(int lopTruoc) {
            this.lopTruoc = lopTruoc;
            return this;
        }

        public int getLopSau() {
            return lopSau;
        }

        public Builder setLopSau(int lopSau) {
            this.lopSau = lopSau;
            return this;
        }

        public int getAcquy() {
            return acquy;
        }

        public Builder setAcquy(int acquy) {
            this.acquy = acquy;
            return this;
        }

        public int getXich() {
            return xich;
        }

        public Builder setXich(int xich) {
            this.xich = xich;
            return this;
        }

        public int getPhanh() {
            return phanh;
        }

        public Builder setPhanh(int phanh) {
            this.phanh = phanh;
            return this;
        }

        public int getPhanhDau() {
            return phanhDau;
        }

        public Builder setPhanhDau(int phanhDau) {
            this.phanhDau = phanhDau;
            return this;
        }

        public int getBugi() {
            return bugi;
        }

        public Builder setBugi(int bugi) {
            this.bugi = bugi;
            return this;
        }

        public int getTamLocGio() {
            return tamLocGio;
        }

        public Builder setTamLocGio(int tamLocGio) {
            this.tamLocGio = tamLocGio;
            return this;
        }

        public int getNuocLamMat() {
            return nuocLamMat;
        }

        public Builder setNuocLamMat(int nuocLamMat) {
            this.nuocLamMat = nuocLamMat;
            return this;
        }

        public Warranty build()
        {
            return new Warranty(this);
        }
    }
}
