package com.tijun.domain;

import java.util.Optional;

public class P {
   private String name ;

   public P(){
   }
   public P(String name){
       this.name = name;
   }

    public Optional<String> getName() {
        return Optional.of(name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
