(ns com.zeddworks.utilities.core-test
  (:use clojure.test
        com.zeddworks.utilities.core))

(deftest contains-any?-test
  (testing "contains-any? with odd? and list (1 2 3) should be true"
    (is
      (= (contains-any? odd? '(1 2 3))
         true)))

  (testing "contains-any? with odd? and list (10 2 32) should be false"
    (is
      (= (contains-any? odd? '(10 2 32))
         false))))

(deftest contains-any-empty-strings?-test
  (testing "contains-any-empty-strings? with map and empty value
           should be true"
    (is
      (= (contains-any-empty-strings?
            (flatten-coll-to-seq
              {:beef "jerkey"
               :fungus ""}))
         true)))

  (testing "contains-any-empty-strings? with map and empty key
           should be true"
    (is
      (= (contains-any-empty-strings?
           (flatten-coll-to-seq
             {:beef "jerkey"
              "" "ted"}))
         true)))

  (testing "contains-any-empty-strings? with map and no empty strings
           should be false"
    (is
      (= (contains-any-empty-strings?
           (flatten-coll-to-seq
             {:jungle "fred"
              :man "bob"}))
         false)))

  (testing "contains-any-empty-strings? with empty map should be false"
    (is
      (= (contains-any-empty-strings? {})
         false)))

  (testing "contains-any-empty-strings? with vector and empty string
           should be true"
    (is
      (= (contains-any-empty-strings?
           ["beef" "" "fungus"])
         true)))

  (testing "contains-any-empty-strings? with vector and no empty strings
           should be false"
    (is
      (= (contains-any-empty-strings?
           ["beef" "jerkey" "fungus"])
         false)))

  (testing "contains-any-empty-strings? with empty vector should be false"
    (is
      (= (contains-any-empty-strings? [])
         false)))

  (testing "contains-any-empty-strings? with list and empty string
           should be true"
    (is
      (= (contains-any-empty-strings?
           '(2 ":beef" "jerkey" ":fungus" "" "george" "kyle"))
         true)))

  (testing "contains-any-empty-strings? with list and no empty string
           should be false"
    (is
      (= (contains-any-empty-strings?
           '(2 ":beef" "jerkey" ":fungus"))
         false)))

  (testing "contains-any-empty-strings? with empty list should be false"
    (is
      (= (contains-any-empty-strings? '())
         false)))

  (testing "contains-any-empty-strings? with set and empty string
           should be true"
    (is
      (= (contains-any-empty-strings?
           #{2 ":beef" "jerkey" "" ":fungus"})
         true)))

  (testing "contains-any-empty-strings? with set and no empty strings
           should be false"
    (is
      (= (contains-any-empty-strings?
           #{2 ":beef" "jerkey" ":fungus"})
         false)))

  (testing "contains-any-empty-strings? with an empty set should be false"
    (is
      (= (contains-any-empty-strings? #{})
         false))))

(deftest flatten-coll-to-seq-test
  (testing "flatten-coll-to-seq with a map should be a seq"
    (is
      (= (flatten-coll-to-seq
           {:beef "jerkey"
            :fungus ""})
         '(:beef "jerkey" :fungus ""))))

  (testing "flatten-coll-to-seq with a vector should be a seq"
    (is
      (= (flatten-coll-to-seq
           ["test" "beef" "fungus" 32 "" :fred])
         '("test" "beef" "fungus" 32 "" :fred))))

  (testing "flatten-coll-to-seq with a list should be a seq"
    (is
      (= (flatten-coll-to-seq
             '("beef" :fungus 1995))
           '("beef" :fungus 1995))))
  (testing "flatten-coll-to-seq with str should be a seq of strings"
    (is
      (= (flatten-coll-to-seq
           '( "hello" :world) str)
           '("hello" ":world")))))

(deftest not-nil?-test
  (testing "not-nil? with nil value should be false"
    (is
        (= (not-nil? nil)
        false)))

  (testing "not-nil? with non nil value should be true"
    (is
      (= (not-nil? "beef-fungus")
      true))))
