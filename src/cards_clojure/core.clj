(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))
(def rank-names {1 :ace, 11 :jack, 12 :queen, 13 :king})

(def test-four-of-a-kind #{{:suit :clubs:rank 1}
                           {:suit :spades :rank 1}
                           {:suit :diamonds :rank 1}
                           {:suit :hearts :rank 1}})

(defn create-deck []
  (set
    (for [suit suits
          rank ranks]
      {:suit suit
       :rank rank})))
;       :rank (get rank-names rank rank)})))

(defn create-hands [deck]
  (set
    (for [c1 deck
          c2 (disj deck c1)
          c3 (disj deck c1 c2)
          c4 (disj deck c1 c2 c3)]
      #{c1 c2 c3 c4})))

(defn flush? [hand]
  (= 1 (count (set (map :suit hand)))))

(defn four-of-a-kind? [hand]
  (= 1 (count (set (map :rank hand)))))
      

(defn -main []
  (let [deck (create-deck)
        hands (create-hands deck)
        flush-hands (filter flush? hands)]
    (count flush-hands)))
