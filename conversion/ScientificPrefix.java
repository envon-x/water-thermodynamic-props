package com.iridiscense.unitoperations.conversion;

/**
 * This is from stackoverflow: https://stackoverflow.com/questions/35631207/how-to-parse-engineering-notation-in-java#35631306
 *  and Stackoverfow get from: https://ideone.com/b65Rzr
 * This class was created for Comanda project
 * Created by bon on 9/24/21.
 * Copyright (c)  Hector Bonifacio. 9/24/21, All rights reserved.
 */
import java.util.regex.*;

enum ScientificPrefix {
    yocto( 'y', 1e-24 ),
    zepto( 'z', 1e-21 ),
    atto( 'a', 1e-18 ),
    femto( 'f', 1e-15 ),
    pico ( 'p', 1e-12 ),
    nano ( 'n', 1e-9 ),
    micro( 'μ', 1e-6 ),
    milli( 'm', 1e-3 ),
    unit ( null, 1e0 ),
    kilo ( 'k', 1e3 ),
    mega ( 'M', 1e6 ),
    giga ( 'G', 1e9 ),
    terra( 'T', 1e12 ),
    peta ( 'P', 1e15 ),
    exa  ( 'E', 1e18 ),
    zetta( 'Z', 1e21 ),
    yotta( 'Y', 1e24 );

    final Character symbol;
    final double multiplier;

    private ScientificPrefix(final Character symbol, final double multiplier ){
        this.symbol     = symbol;
        this.multiplier = multiplier;
    }

    public Character getSymbol(){ return symbol; }
    public double getMultiplier(){ return multiplier; }

    private static final Pattern REGEX;

    static {
        final StringBuffer buffer = new StringBuffer();
        buffer.append( "^([+-]?[1-9]\\d*\\.?\\d*|[+-]?0?\\.\\d+)(?:([" );
        for ( final ScientificPrefix e : values() )
            if ( e.getSymbol() != null )
                buffer.append( e.getSymbol() );
        buffer.append( "]?)|E([+-]?[1-9]\\d*))$" );
        REGEX = Pattern.compile( buffer.toString() );
    }

    public static Double parse( final String value ){
        final Matcher m = REGEX.matcher( value );
        if ( !m.matches() )
            return null;
        Double result = Double.parseDouble( m.group(1) );
        if ( m.group(3) != null )
            return result * Math.pow( 10, Integer.parseInt( m.group(3) ) );
        if ( m.group(2) == null )
            return result; // Units
        final Character c = m.group(2).charAt(0);
        for ( final ScientificPrefix e : values() )
            if ( e.getSymbol() == c )
                return result * e.getMultiplier();
        return null;
    }

    private static String doubleToString( final double value ){
        if ( value == (long) value )
            return String.format( "%d", (long) value );
        return String.format( "%s", value );
    }

    public static String toEngineeringNotation(final double value, final ScientificPrefix notation){
        if ( notation == null || notation == unit )
            return doubleToString( value );
        return doubleToString( value / notation.getMultiplier() ) + notation.getSymbol();
    }

    public static String toScientificNotation(final double value) {
        final long exponent = (long) Math.floor( Math.log10( Math.abs( value ) ) );
        return doubleToString( value / Math.pow( 10, exponent ) ) + 'E' + exponent;
    }

    public static String toEngineeringNotation(final double value) {
        final double abs = Math.abs( value );
        double multiplier;
        for ( final ScientificPrefix e : values() )
        {
            multiplier = e.getMultiplier();
            if ( multiplier < abs && abs < multiplier * 1000 )
                return toEngineeringNotation( value, e );
        }
        return toScientificNotation( value );
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        final String[] parseTests = {
                "1.23M",
                "1.23E",
                "1.23E5",
                "1.23E+5",
                "-0.123E-28"
        };
        for ( final String test : parseTests )
            System.out.println( test + " parses to: " + Double.toString(parse(test)));

        final double[] formatTests = {
                1234e18,
                -12.34e-26,
                100,
                0.1
        };

        for ( final double test : formatTests )
            System.out.println( Double.toString( test ) + " formats as " + toEngineeringNotation( test ) );
    }
}

/**
  stdout copy
    1.23M parses to: 1230000.0
    1.23E parses to: 1.23E18
    1.23E5 parses to: 123000.0
    1.23E+5 parses to: 123000.0
    -0.123E-28 parses to: -1.23E-29

    1.234E21 formats as 1.234Z
    -1.234E-25 formats as -1.234E-25
    100.0 formats as 100
    0.1 formats as 100m
 **/