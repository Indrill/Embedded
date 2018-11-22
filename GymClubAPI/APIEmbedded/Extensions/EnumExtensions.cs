﻿
using System;
using System.ComponentModel;
using System.Reflection;

namespace APIEmbedded.Extensions
{
    public static partial class EnumExtensions
    {
        public static string ToStringName(this Enum value)
        {
            FieldInfo fi = value.GetType().GetField(value.ToString());

            DescriptionAttribute[] attributes =
           (DescriptionAttribute[])fi.GetCustomAttributes(typeof(DescriptionAttribute), false);

            if (attributes != null && attributes.Length > 0)
                return attributes[0].Description;
            else
                return value.ToString();
        }
    }
}
