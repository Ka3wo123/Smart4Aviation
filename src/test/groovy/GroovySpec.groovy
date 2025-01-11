import spock.lang.Specification
import spock.lang.Unroll

class GroovySpec extends Specification {
    @Unroll
    def "should return sum = #expectedSum for array: #array and range <#start, #end>"() {
        given: "simple array of integers"
        int[] arr = array

        when: "segment tree is constructed and sum for range is queried"
        def construct = SegmentTree.construct(arr, 0, arr.size() - 1)
        def sum = SegmentTree.getSum(construct, start, end)

        then: "sum of the range should be correct"
        sum == expectedSum

        where:
        array             | start | end | expectedSum
        [10, 2, 3, 4]     | 0     | 3   | 19
        [2, 5, 6, 6]      | 1     | 2   | 11
        [193, 100, 78, 4] | 0     | 0   | 193
        [66, 77, 88, 99]  | 3     | 3   | 99
    }

    def "should return sum 0 for a range outside the bounds"() {
        given: "simple array of integers"
        int[] arr = [1, 2, 3, 4]

        when: "segment tree is constructed and query is made for a range outside bounds"
        def construct = SegmentTree.construct(arr, 0, arr.size() - 1)
        def sum = SegmentTree.getSum(construct, 5, arr.size() + 10)

        then: "sum out of the bounds should be 0"
        sum == 0
    }

    @Unroll
    def "should update by value = #newValue at index #index and return sum #expectedSum in queried range <#start, #end>"() {
        given: "simple array of integers"
        int[] arr = array

        when: "segment tree is constructed and value is about to update"
        def construct = SegmentTree.construct(arr, 0, arr.size() - 1)
        SegmentTree.update(construct, index, newValue)
        def sum = SegmentTree.getSum(construct, start, end)

        then: "sum after update should be correct"
        sum == expectedSum

        where:
        array             | index | newValue | start | end | expectedSum
        [10, 2, 3, 4]     | 0     | 0        | 0     | 3   | 9
        [2, 5, 6, 6]      | 1     | 6        | 1     | 2   | 12
        [193, 100, 78, 4] | 2     | 0        | 0     | 0   | 193
        [66, 77, 88, 99]  | 3     | 8        | 3     | 3   | 8
    }

}
