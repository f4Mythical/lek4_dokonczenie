package com.example.lek4;

public class Pytanie {
        private int idobrazek;
        private String trescPytania;
        private boolean odpowiedzi;
        private String podpowiedzi;
        private boolean czyOdpOK;
        private boolean czyWykorzystanaPodpowiedz;

        public Pytanie(int idobrazek, String trescPytania, boolean odpowiedzi, String podpowiedzi) {
                this.idobrazek = idobrazek;
                this.trescPytania = trescPytania;
                this.odpowiedzi = odpowiedzi;
                this.podpowiedzi = podpowiedzi;
                this.czyOdpOK = false;
                this.czyWykorzystanaPodpowiedz = false;
        }

        public String getTrescPytania() {
                return trescPytania;
        }

        public void setTrescPytania(String trescPytania) {
                this.trescPytania = trescPytania;
        }

        public int getIdobrazek() {
                return idobrazek;
        }

        public void setIdobrazek(int idobrazek) {
                this.idobrazek = idobrazek;
        }

        public boolean isOdpowiedzi() {
                return odpowiedzi;
        }

        public void setOdpowiedzi(boolean odpowiedzi) {
                this.odpowiedzi = odpowiedzi;
        }

        public String getPodpowiedzi() {
                return podpowiedzi;
        }

        public void setPodpowiedzi(String podpowiedzi) {
                this.podpowiedzi = podpowiedzi;
        }

        public boolean isCzyOdpOK() {
                return czyOdpOK;
        }

        public void setCzyOdpOK(boolean czyOdpOK) {
                this.czyOdpOK = czyOdpOK;
        }

        public boolean isCzyWykorzystanaPodpowiedz() {
                return czyWykorzystanaPodpowiedz;
        }

        public void setCzyWykorzystanaPodpowiedz(boolean czyWykorzystanaPodpowiedz) {
                this.czyWykorzystanaPodpowiedz = czyWykorzystanaPodpowiedz;
        }
}