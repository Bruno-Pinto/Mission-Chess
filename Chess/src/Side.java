public enum Side {White, Black;
    public Side getOpponent(){
        return this == White ? Black : White;
    }
}
