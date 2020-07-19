package org.example.model;

import com.google.gson.annotations.SerializedName;

public class Games {
    @SerializedName("Lotto")
    private Lotto lotto;

    @SerializedName("Mini")
    private Mini mini;

    @SerializedName("LottoPlus")
    private LottoPlus lottoPlus;

    @SerializedName("EkstraPensja")
    private EkstraPensja ekstraPensja;

    @SerializedName("Joker")
    private Joker joker;

    public Lotto getLotto() {
        return lotto;
    }

    public void setLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    public Mini getMini() {
        return mini;
    }

    public void setMini(Mini mini) {
        this.mini = mini;
    }

    public LottoPlus getLottoPlus() {
        return lottoPlus;
    }

    public void setLottoPlus(LottoPlus lottoPlus) {
        this.lottoPlus = lottoPlus;
    }

    public EkstraPensja getEkstraPensja() {
        return ekstraPensja;
    }

    public void setEkstraPensja(EkstraPensja ekstraPensja) {
        this.ekstraPensja = ekstraPensja;
    }

    public Joker getJoker() {
        return joker;
    }

    public void setJoker(Joker joker) {
        this.joker = joker;
    }

    @Override
    public String toString() {
        return "Games{" +
                "lotto=" + lotto +
                ", mini=" + mini +
                ", lottoPlus=" + lottoPlus +
                ", ekstraPensja=" + ekstraPensja +
                ", joker=" + joker +
                '}';
    }
}
