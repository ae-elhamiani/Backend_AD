//package com.eainfo.clientService.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class OcrResponse {
//    private String codeRetour;
//    private String messageRetour;
//    private String codeClient;
//    private DocumentData documentDto;
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class DocumentData {
//        private String typeDocument;
//        private String formatDocument;
//        private String nomDocument;
//        private String anneDelivrance;
//        private String codePaysDelivrance;
//        private String paysDelivrance;
//        private String score;
//        private String dateNaissance;
//        private String numeroDocument;
//        private String dateExpiration;
//        private String dateDelivrance;
//        private String lieuNaissance;
//        private String numeroPersonel;
//        private String nom;
//        private String prenom;
//        private String nomMere;
//        private String nationalite;
//        private String codeNationalite;
//        private String sexe;
//        private String address;
//        private String numeroSecuriteSociale;
//        private String visaId;
//        private String authorite;
//        private String nomPere;
//        private String numeroDocIdentite;
//        private String age;
//        private String ageAvoirDoc;
//        private String imagePortrait;
//        private String signature;
//        private int moisPourExpire;
//        private String imageFront;
//        private String imageBack;
//        private boolean isSameDoc;
//        private CarteGrise carteGrise;
//        private PermisConduire permisConduire;
//        private DataArabe dataArabe;
//
//        @Data
//        @AllArgsConstructor
//        @NoArgsConstructor
//        @Builder
//        public static class CarteGrise {
//            private String proprietaireVehicle;
//            private String premierMiseEnCir;
//            private String miseEnCirculationMar;
//            private String marqueVehicle;
//            private String typeVehicle;
//            private String typeCarburant;
//            private String categoryVehicle;
//            private String numChassis;
//            private String referenceNumber;
//            private String ancienMatricule;
//            private String matricule;
//            // Constructors, getters, and setters...
//        }
//
//        @Data
//        @AllArgsConstructor
//        @NoArgsConstructor
//        @Builder
//        public static class PermisConduire {
//            private String numPermis;
//            private List<Category> listCategory;
//
//
//
//            @Data
//            @AllArgsConstructor
//            @NoArgsConstructor
//            @Builder
//            public static class Category {
//                private String categoriePermis;
//                private String dateDelivrencePermis;
//            }
//        }
//
//        @Data
//        @AllArgsConstructor
//        @NoArgsConstructor
//        @Builder
//        public static class DataArabe {
//            private String lieuNaissance;
//            private String nom;
//            private String prenom;
//            private String nomMere;
//            private String address;
//            private String nomPere;
//        }
//    }
//
//}
