/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Enums;

/**
 *
 * @author timtim
 */
public enum Position {
    A1, B1, C1, D1, E1, F1, G1, H1,
    A2, B2, C2, D2, E2, F2, G2, H2,
    A3, B3, C3, D3, E3, F3, G3, H3,
    A4, B4, C4, D4, E4, F4, G4, H4,
    A5, B5, C5, D5, E5, F5, G5, H5,
    A6, B6, C6, D6, E6, F6, G6, H6,
    A7, B7, C7, D7, E7, F7, G7, H7,
    A8, B8, C8, D8, E8, F8, G8, H8;

    @Override
    public String toString() {
        switch(this){
            case A1:
                return "a1";
            case A2:
                return "a2";
            case A3:
                return "a3";
            case A4:
                return "a4";
            case A5:
                return "a5";
            case A6:
                return "a6";
            case A7:
                return "a7";
            case A8:
                return "a8";
            case B1:
                return "b1";
            case B2:
                return "b2";
            case B3:
                return "b3";
            case B4:
                return "b4";
            case B5:
                return "b5";
            case B6:
                return "b6";
            case B7:
                return "b7";
            case B8:
                return "b8";
            case C1:
                return "c1";
            case C2:
                return "c2";
            case C3:
                return "c3";
            case C4:
                return "c4";
            case C5:
                return "c5";
            case C6:
                return "c6";
            case C7:
                return "c7";
            case C8:
                return "c8";
            case D1:
                return "d1";
            case D2:
                return "d2";
            case D3:
                return "d3";
            case D4:
                return "d4";
            case D5:
                return "d5";
            case D6:
                return "d6";
            case D7:
                return "d7";
            case D8:
                return "d8";
            case E1:
                return "e1";
            case E2:
                return "e2";
            case E3:
                return "e3";
            case E4:
                return "e4";
            case E5:
                return "e5";
            case E6:
                return "e6";
            case E7:
                return "e7";
            case E8:
                return "e8";
            case F1:
                return "f1";
            case F2:
                return "f2";
            case F3:
                return "f3";
            case F4:
                return "f4";
            case F5:
                return "f5";
            case F6:
                return "f6";
            case F7:
                return "f7";
            case F8:
                return "f8";
            case G1:
                return "g1";
            case G2:
                return "g2";
            case G3:
                return "g3";
            case G4:
                return "g4";
            case G5:
                return "g5";
            case G6:
                return "g6";
            case G7:
                return "g7";
            case G8:
                return "g8";
            case H1:
                return "h1";
            case H2:
                return "h2";
            case H3:
                return "h3";
            case H4:
                return "h4";
            case H5:
                return "h5";
            case H6:
                return "h6";
            case H7:
                return "h7";
            case H8:
                return "h8";           
                
            default:
                return "";
        }
    }
     
    /**
     * Gibt an, ob Position zur Grundreihe des Gegners gehört. 
     * Wichtig bei Umwandlung von Bauer
     * 
     * @param spielerfarbe
     * @return 
     */
    public boolean istGundreiheAndereSeite(Farbe spielerfarbe) {
        
        if(spielerfarbe == Farbe.SCHWARZ){
            switch(this){
                case A1:
                case B1:     
                case C1:
                case D1:         
                case E1:
                case F1:         
                case G1:
                case H1:
                    return true;

                default:
                    return false;
            }
        }
        else{
            switch(this){
                case A8:
                case B8:     
                case C8:
                case D8:         
                case E8:
                case F8:         
                case G8:
                case H8:
                    return true;

                default:
                    return false;
            }
        }
    }
    
}
