import pl.smart4aviation.model.Plane
import pl.smart4aviation.util.SegmentTree
import spock.lang.Specification

class RouteOperationsSpec extends Specification {
    def "example 1: validate operations for 5 planes and 7 queries"() {
        given: "list of planes and segment tree"
        def planes = [
                new Plane(1), new Plane(2), new Plane(3), new Plane(2), new Plane(4)
        ]
        def segmentTree = new SegmentTree(planes)
        def days = [2, 3, 4, 6, 8]

        expect:
        segmentTree.getSum(planes, 1, 5, days[0]) == 24
        segmentTree.getSum(planes, 2, 3, days[0]) == 10

        when: "performing operations to cancel and update routes"
        segmentTree.updateValue(planes, 2, 0, days[1])
        segmentTree.updateValue(planes, 3, 5, days[1])

        then:
        segmentTree.getSum(planes, 2, 4, days[2]) == 22

        when: "assigning a new max passenger seats to route"
        segmentTree.updateValue(planes, 2, 5, days[3])

        then:
        segmentTree.getSum(planes, 1, 5, days[4]) == 97
    }

    def "example 2: validate operations for 1 plane and 7 queries"() {
        given: "one plane and segment tree"
        List<Plane> planes = [new Plane(2)]
        def segmentTree = new SegmentTree(planes)
        def days = [1, 2, 3, 4, 7, 8]

        expect:
        segmentTree.getSum(planes, 1, 1, days[0]) == 2

        when: "performing operations to cancel and assign routes"
        segmentTree.updateValue(planes, 1, 0, days[0])
        segmentTree.updateValue(planes, 1, 6, days[1])

        then: "calculating sums since 1st day till specified inclusively (withdrawn planes' max seats are excluded)"
        segmentTree.getSum(planes, 1, 1, days[2]) == 12
        segmentTree.getSum(planes, 1, 1, days[3]) == 18
        segmentTree.getSum(planes, 1, 1, days[4]) == 36
        segmentTree.getSum(planes, 1, 1, days[5]) == 42
    }
}
