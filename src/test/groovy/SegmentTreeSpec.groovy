import pl.smart4aviation.models.Plane
import pl.smart4aviation.utils.SegmentTree
import spock.lang.Specification
import spock.lang.Unroll

class SegmentTreeSpec extends Specification {

    @Unroll
    def "should compute sum of available passenger seats equal to #expectedSum for range <#start, #end>"() {
        given: "segment tree of given list of planes"
        def segmentTree = constructSegmentTree(list)

        when: "sum for range is queried"
        def sum = segmentTree.getSum(start, end)

        then:
        sum == expectedSum

        where:
        list                                                          | start | end | expectedSum
        [new Plane(10), new Plane(2), new Plane(3), new Plane(4)]     | 1     | 3   | 15
        [new Plane(2), new Plane(5), new Plane(6), new Plane(6)]      | 1     | 2   | 7
        [new Plane(193), new Plane(100), new Plane(78), new Plane(4)] | 1     | 1   | 193
        [new Plane(66), new Plane(77), new Plane(88), new Plane(99)]  | 3     | 3   | 88
    }

    def "should return sum of available passenger seats equal to 0 for a range outside the bounds"() {
        given: "segment tree of given list of planes"
        def list = [new Plane(1), new Plane(2), new Plane(3), new Plane(4)]
        def segmentTree = constructSegmentTree(list)

        when: "query is made for a range outside bounds"
        def sum = segmentTree.getSum(5, list.size() + 10)

        then:
        sum == 0
    }

    @Unroll
    def "should change available passenger seats to #newValue for plane at index #updateIndex"() {
        given: "segment tree of given list of planes"
        def segmentTree = constructSegmentTree(list)

        when: "value is updated and sum for range is queried"
        segmentTree.updateValue(list, updateIndex, newValue)

        then:
        list.get(updateIndex - 1).getMaxPassengers() == newValue
        list.get(updateIndex - 1).isActive()

        where:
        list                                                         | updateIndex | newValue
        [new Plane(10), new Plane(2), new Plane(3), new Plane(4)]    | 1           | 1
        [new Plane(2), new Plane(5), new Plane(6), new Plane(6)]     | 1           | 6
        [new Plane(66), new Plane(77), new Plane(88), new Plane(99)] | 3           | 8
    }

    def "should cancel flight for plane"() {
        given:
        def list = [new Plane(10), new Plane(5)]
        def segmentTree = constructSegmentTree(list)
        def updateIndex = 1
        def newValue = 0
        def startIndex = 1
        def endIndex = list.size()

        when:
        segmentTree.updateValue(list, updateIndex, newValue)

        then:
        segmentTree.getSum(startIndex, endIndex) == 5

        and: "plane at given updateIndex should be inactive"
        !list.get(updateIndex - 1).isActive()

    }

    private static def constructSegmentTree(List<Plane> list) {
        new SegmentTree(list)
    }
}
