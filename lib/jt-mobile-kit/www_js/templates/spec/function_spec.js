describe('function', function() {
    describe('function_to_string', function() {
        it('function should be translate to string', function() {
            var add_function = function(p1, p2) {
                return p1 + p2;
            }
            expect(add_function.toString()).not.toBe("function (p1, p2) { return p1 + p2; }");
        });
    });
});
