import pl.smart4aviation.model.Plane
import pl.smart4aviation.utils.SegmentTree
import spock.lang.Specification

class RouteOperationsSpec extends Specification {
    def "example 1: validate operations for 5 planes and 7 queries"() {
        given: "list of planes and segment tree"
        def planes = [
                new Plane(1), new Plane(2), new Plane(3), new Plane(2), new Plane(4)
        ]
        def segmentTree = new SegmentTree(planes)

        expect:
        segmentTree.getSum(1, 5) == 24
        segmentTree.getSum(2, 3) == 10

        when: "performing operations to cancel and update routes"
        segmentTree.updateValue(planes, 2, 0)
        segmentTree.updateValue(planes, 3, 5)

        then:
        segmentTree.getSum(2, 4) == 22

        when: "assigning a new max passenger seats to route"
        segmentTree.updateValue(planes, 2, 5)

        then:
        segmentTree.getSum(1, 5) == 100
    }

    def "example 2: validate operations for 1 plane and 7 queries"() {
        given: "one plane and segment tree"
        def planes = [new Plane(2)]
        def segmentTree = new SegmentTree(planes)

        expect:
        segmentTree.getSum(1, 1) == 2

        when: "performing operations to cancel and assign routes"
        segmentTree.updateValue(planes, 1, 0)
        segmentTree.updateValue(planes, 1, 6)

        then:
        segmentTree.getSum(1, 1) == 6
        segmentTree.getSum(1, 1) == 12
        segmentTree.getSum(1, 1) == 30
        segmentTree.getSum(1, 1) == 36
    }
}
