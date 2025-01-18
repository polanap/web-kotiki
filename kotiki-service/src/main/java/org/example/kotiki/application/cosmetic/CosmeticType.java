package org.example.kotiki.application.cosmetic;

public enum CosmeticType {
    BACKGROUND{
        @Override
        public boolean disapplieble(){return true;}
    },
    MUZZLE{
        @Override
        public boolean disapplieble(){return false;}
    },
    COSTUME{
        @Override
        public boolean disapplieble(){return false;}
    },
    HEAD{
        @Override
        public boolean disapplieble(){return true;}
    },
    BODY{
        @Override
        public boolean disapplieble(){return true;}
    },
    TAIL{
        @Override
        public boolean disapplieble(){return true;}
    };
    public abstract boolean disapplieble();
}
